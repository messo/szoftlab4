#!/bin/bash

PATH=$PATH:/c/Program\ Files/Java/jdk1.6.0_24/bin

javac -encoding UTF-8 -classpath ~/Dokumentumok/tools.jar latex.java 

javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSim/src hu.override.logsim | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > hu.override.logsim.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSim/src hu.override.logsim.component | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' > hu.override.logsim.component.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSim/src hu.override.logsim.component.impl | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' > hu.override.logsim.component.impl.tex
#javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSim/src hu.override.logsim.controller | \
#sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' > hu.override.logsim.controller.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSim/src hu.override.logsim.parser | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' > hu.override.logsim.parser.tex
