plugins {
	java
}

dependencies {
	implementation(project(":kevin-api"))
	implementation(project(":kevin-common"))

	compileOnly("net.md-5:bungeecord-api:${property("bungeeVersion")}")
}

tasks.processResources {
	filesMatching("**/*.yml") {
		expand(
			"version" to project.version,
			"description" to project.description
		)
	}
}