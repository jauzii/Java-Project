@echo off
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot
"%JAVA_HOME%\bin\java" -jar ".mvn\wrapper\maven-wrapper.jar" %*
