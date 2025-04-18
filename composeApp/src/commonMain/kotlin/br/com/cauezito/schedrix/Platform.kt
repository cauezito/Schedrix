package br.com.cauezito.schedrix

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform