package demo

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.LinkedList
import java.util.Queue
import kotlin.collections.HashMap

val cache = HashMap<String, Set<String>>()

fun main() {
    print("Introduce el primer enlace de Wikipedia: ")
    val firstUrl = readLine()?.let { encodeToUnicode(it).replace("\\s".toRegex(), "") }

    if (firstUrl != null && isValidWikipediaLink(firstUrl)) {
        print("Introduce el segundo enlace de Wikipedia: ")
        val secondUrl = readLine()?.let { encodeToUnicode(it).replace("\\s".toRegex(), "") }

        if (secondUrl != null && isValidWikipediaLink(secondUrl)) {
            val startTime = System.currentTimeMillis()
            println("Fecha de inicio: ${java.util.Date(startTime)}")

            val path = findShortestPath(firstUrl, secondUrl)
            if (path != null) {
                val decodedPath = path.map { decodeFromUnicode(it) }
                println("Ruta encontrada: ${decodedPath.joinToString(" -> ")}")
            } else {
                println("No se encontró ninguna ruta entre las dos páginas.")
            }

            val endTime = System.currentTimeMillis()
            val duration = endTime - startTime

            val hours = duration / 3600000
            val minutes = (duration % 3600000) / 60000
            val seconds = (duration % 60000) / 1000
            val milliseconds = duration % 1000

            println("Fecha de finalización: ${java.util.Date(endTime)}")
            println("Duración: ${hours}h ${minutes}m ${seconds}s ${milliseconds}ms")

        } else {
            println("Segundo enlace no válido. Por favor, introduce un enlace de Wikipedia en español.")
        }
    } else {
        println("Primer enlace no válido. Por favor, introduce un enlace de Wikipedia en español.")
    }
}

fun decodeFromUnicode(textoCodificado: String): String {
    return if (textoCodificado != "https://es.wikipedia.org/wiki/%") {
        URLDecoder.decode(textoCodificado, StandardCharsets.UTF_8.toString())
    } else {
        textoCodificado
    }
}

fun encodeToUnicode(encodedText: String): String {
    val correctedText = encodedText.replace("%", "%25")
    return URLDecoder.decode(correctedText, StandardCharsets.UTF_8.toString())
}

fun isValidWikipediaLink(url: String?): Boolean {
    return url != null && url.startsWith("https://es.wikipedia.org/wiki/")
}

fun extractWikipediaLinks(url: String): Set<String> {
    val correctedUrl = if (url == "https://es.wikipedia.org/wiki/%") {
        url.replace("https://es.wikipedia.org/wiki/%", "https://es.wikipedia.org/wiki/%25")
    } else url

    cache[correctedUrl]?.let { return it }

    val document: Document = Jsoup.connect(correctedUrl).get()
    val links = document.select("a[href]")
    val uniqueLinks = mutableSetOf<String>()

    for (link in links) {
        val href = link.attr("abs:href")
        if (href.contains("wikipedia.org/wiki/") &&
            href != correctedUrl &&
            (href.substringAfter("wikipedia.org/wiki/").contains("Anexo:") ||
                    !href.substringAfter("wikipedia.org/wiki/").contains(":")) &&
            href.contains("https://es.") &&
            !href.contains("#")) {
            uniqueLinks.add(href)
        }
    }

    cache[correctedUrl] = uniqueLinks.map { encodeToUnicode(it) }.toSet()
    return cache[correctedUrl]!!
}

fun findShortestPath(startUrl: String, targetUrl: String): List<String>? {
    val queue: Queue<List<String>> = LinkedList()
    val visited: MutableSet<String> = mutableSetOf()
    var level = 0

    queue.add(listOf(startUrl))
    visited.add(startUrl)

    while (queue.isNotEmpty()) {
        val currentLevelSize = queue.size
        println("Nivel $level: explorando ${currentLevelSize} páginas")

        for (i in 0 until currentLevelSize) {
            val path = queue.poll()
            val currentUrl = path.last()

            if (currentUrl == targetUrl) {
                return path
            }

            val links = extractWikipediaLinks(currentUrl)

            for (link in links) {
                if (!visited.contains(link)) {
                    visited.add(link)
                    val newPath = path.toMutableList()
                    newPath.add(link)
                    queue.add(newPath)
                }
            }
        }
        level++
    }
    return null
}
