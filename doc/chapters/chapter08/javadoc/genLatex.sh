#!/bin/bash

javac -encoding UTF-8 -classpath ~/tools.jar latex.java 

javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimProto/src logsim | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimProto/src logsim.model | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim.model.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimProto/src logsim.model.component | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim.model.component.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimProto/src logsim.model.component.impl | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim.model.component.impl.tex

