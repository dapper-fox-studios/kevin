plugins {
    java
}

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:5.0.0")
    }
}

allprojects {
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "java")

	group = "co.schemati.trevor"
	version = "2.0.1-SNAPSHOT"
    description = "Trevor is a platform-independent solution for cross-instance proxy communication"

	/*sourceCompatibility = JavaVersion.VERSION_11
	targetCompatibility = JavaVersion.VERSION_11*/

    extra["configurateVersion"] = "3.7.1"
    extra["gsonVersion"] = "2.8.5"
    extra["guavaVersion"] = "28.2-jre"
    extra["jedisVersion"] = "3.5.2"

    extra["bungeeVersion"] = "1.16-R0.5-SNAPSHOT"
    extra["velocityVersion"] = "1.1.4"
	/*ext {
        // Library versions
        configurateVersion = "3.7.1";
        gsonVersion = "2.8.5";
        guavaVersion = "28.2-jre";
        jedisVersion = '3.5.2';

        // Platform versions
		bungeeVersion = '1.16-R0.5-SNAPSHOT'
		velocityVersion = '1.1.4'
	}*/
	
	repositories {
		mavenLocal()
		mavenCentral()

        maven("https://oss.sonatype.org/content/repositories/snapshots")
	}

    dependencies {
        compileOnly("com.google.guava:guava:${property("guavaVersion")}") {
            isTransitive = true
        }
        compileOnly("com.google.code.gson:gson:${property("gsonVersion")}") {
            isTransitive = true
        }
    }

    tasks.named("build") {
        dependsOn(tasks.named("shadowJar"))
    }
}