
NAME = "jotto/Jotto"

all:
	@echo "Compiling..."
	javac jotto/*.java

run: all
	@echo "Running..."
	java $(NAME)

clean:
	rm -rf *.class jotto/*.class