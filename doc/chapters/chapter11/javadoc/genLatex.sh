#!/bin/bash

javac -encoding UTF-8 -classpath ~/tools.jar latex.java 

javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimGui/src logsim | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim2.tex
iconv -f iso-8859-2 -t utf-8 logsim2.tex > logsim.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimGui/src logsim.model | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim2.model.tex
iconv -f iso-8859-2 -t utf-8 logsim2.model.tex > logsim.model.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimGui/src logsim.model.component | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim2.model.component.tex
iconv -f iso-8859-2 -t utf-8 logsim2.model.component.tex > logsim.model.component.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimGui/src logsim.model.component.impl | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim2.model.component.impl.tex
iconv -f iso-8859-2 -t utf-8 logsim2.model.component.impl.tex > logsim.model.component.impl.tex

javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimGui/src logsim.view | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim2.view.tex
iconv -f iso-8859-2 -t utf-8 logsim2.view.tex > logsim.view.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimGui/src logsim.view.component | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim2.view.component.tex
iconv -f iso-8859-2 -t utf-8 logsim2.view.component.tex > logsim.view.component.tex
javadoc -encoding iso-8859-2 -private -doclet latex -sourcepath ../../../../LogSimGui/src logsim.view.component.impl | \
sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' | sed 's/{@link \([^}]*\)}/\\texttt{\1}/' | sed 's/#/\\#/' > logsim2.view.component.impl.tex
iconv -f iso-8859-2 -t utf-8 logsim2.view.component.impl.tex > logsim.view.component.impl.tex
