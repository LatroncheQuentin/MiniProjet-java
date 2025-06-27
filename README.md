ligne de code pour la compilation pour la javadoc :
javadoc -d doc -sourcepath src fr.ildeilc.model fr.ildeilc.controller fr.ildeilc.view

prérequis pour les tests unitaires:
Télécharge le JAR de JUnit 5 (Standalone)
Va ici :
https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.9.0/

Télécharge ce fichier :
junit-platform-console-standalone-1.9.0.jar

Copie ce fichier dans un dossier de ton projet, par exemple lib/

ligne pour exécuter les tests unitaires :
chmod +x run_tests.sh
./run_tests.sh
