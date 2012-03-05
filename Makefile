JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $<

CLASSES = Hello.class

all: $(CLASSES)

clean:
	/bin/rm Hello.class
