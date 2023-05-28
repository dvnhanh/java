build-java:
	javac -d . Main.java
	jar -cf manage_student.jar Main.class
run-java:
	javac -d . Main.java
	java Main