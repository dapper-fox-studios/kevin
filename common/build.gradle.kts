plugins {
	java
}

dependencies {
	implementation(project(":kevin-api"))

	implementation("org.spongepowered:configurate-yaml:${property("configurateVersion")}")

	implementation("redis.clients:jedis:${property("jedisVersion")}")
}