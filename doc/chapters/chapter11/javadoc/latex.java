/**
 * $ javac -classpath /usr/lib/jvm/java-6-sun/lib/tools.jar latex.java 
 * $ javadoc -private -doclet latex ../src/{controller,view}/*.java | \
 *   iconv -t latin2 | sed -e '1,/==== CUT/ d' -e '/==== END/,$ d' >doc.tex
 */
import com.sun.javadoc.*;
import java.util.Arrays;

public class latex extends Doclet {

	public static boolean start(RootDoc root) {
		System.out.println("==== CUT HERE =====");

		ClassDoc[] classes = root.classes();
		Arrays.sort(classes);
		for (ClassDoc cd : classes) {
			System.out.println("\\subsection{" + cd.name() + "}");
			if (cd.isInterface()) {
				System.out.println("Interfész.");
			} else if (cd.isAbstract()) {
				System.out.println("Absztrakt osztály.");
			}

			System.out.println("\\begin{itemize}");
			System.out.println("\\item Felelősség\\\\");
			System.out.println(cd.commentText().replace('\n', ' '));
			if (cd.commentText().length() < 5) {
				System.out.println(" % TODO");
			}
			System.out.println("\\item Ősosztályok\\ " + getSupers(cd) + ".");
			System.out.println("\\item Interfészek " + getIfaces(cd));
			if (!cd.isInterface()) {
				System.out.println("\\item Attribútumok $\\ $\n\\begin{itemize}");
				printFields(cd.fields());
				System.out.println("\\end{itemize}");
			}
			System.out.println("\\item Metódusok$\\ $\n\\begin{itemize}");
			printMembers(cd);
			System.out.println("\\end{itemize}");
			System.out.println("\\end{itemize}");
			System.out.println();
		}
		System.out.println("==== END CUT HERE =====");
		return true;
	}

	private static String getIfaces(ClassDoc cd) {
		ClassDoc[] ifs = cd.interfaces();
		String s = null;
		Arrays.sort(ifs);
		for (ClassDoc iface : ifs) {
			s = (s == null ? "" : s + ", ") + iface.name();
		}
		return s == null ? "(nincs)" : (s + ".");
	}

	private static String getSupers(ClassDoc cl) {
		ClassDoc sup = cl.superclass();
		if (sup != null) {
			return getSupers(sup) + " $\\rightarrow{}$ " + cl.name();
		} else {
			return cl.name();
		}

	}

	private static void printFields(FieldDoc[] mems) {
		Arrays.sort(mems);
		for (FieldDoc mem : mems) {
			String sign = "";
			String modif = mem.modifiers();
			if(modif.startsWith("public")) {
				sign = "$+$";
				modif = modif.substring("public".length());
			} else if(modif.startsWith("private")) {
				sign = "$-$";
				modif = modif.substring("private".length());
			} else if(modif.startsWith("protected")) {
				sign = "$#$";
				modif = modif.substring("protected".length());
			} 

			String uStart = "";
			String uEnd = "";
			if(modif.startsWith(" static")) {
				modif = modif.substring(" static".length());
				uStart = "\\underline{";
				uEnd = "}";
			}

			System.out.print("\t\\item[] \\texttt{" + sign + modif + " "
				+ getTypeName(mem.type()) + " " + uStart + mem.name().replace("_", "\\_") +uEnd + "} ");
			System.out.println(mem.commentText().replace('\n', ' '));
			if (mem.commentText().length() < 5) {
				System.out.println(" % TODO");
			}
		}
		if (mems.length == 0) {
			System.out.println("\\item (nincs)");
		}
	}

	static void printMembers(ClassDoc cd) {
		int count = 0;
		ConstructorDoc[] constrs = cd.constructors();
		Arrays.sort(constrs);
		for (ConstructorDoc mem : constrs) {
			String sign = "";
			String modif = mem.modifiers();
			if(modif.startsWith("public")) {
				sign = "$+$";
				modif = modif.substring("public".length());
			} else if(modif.startsWith("private")) {
				sign = "$-$";
				modif = modif.substring("private".length());
			} else if(modif.startsWith("protected")) {
				sign = "$#$";
				modif = modif.substring("protected".length());
			}

			String uStart = "";
			String uEnd = "";
			if(modif.startsWith(" static")) {
				modif = modif.substring(" static".length());
				uStart = "\\underline{";
				uEnd = "}";
			}

			if (mem.name().compareTo("toString") != 0 /*&& mem.isPublic()*/) {
				count++;
				System.out.print("\t\\item[] \\texttt{" + sign + modif + " "
					+ uStart + mem.name().replace("_", "\\_") + uEnd + "(" + getParams(mem).replace("_", "\\_") + ")}: ");
				System.out.println(mem.commentText().replace('\n', ' '));
				if (mem.commentText().length() < 5) {
					System.out.println(" % TODO");
				}
			}
		}
		MethodDoc[] mems = cd.methods();
		Arrays.sort(mems);
		for (MethodDoc mem : mems) {
			String sign = "";
			String modif = mem.modifiers();
			if(modif.startsWith("public")) {
				sign = "$+$";
				modif = modif.substring("public".length());
			} else if(modif.startsWith("private")) {
				sign = "$-$";
				modif = modif.substring("private".length());
			} else if(modif.startsWith("protected")) {
				sign = "$#$";
				modif = modif.substring("protected".length());
			} 
			if (mem.name().compareTo("toString") != 0 /*&& mem.isPublic()*/) {
				count++;
				System.out.print("\t\\item[] \\texttt{" + sign + modif + " "
					+ getTypeName(mem.returnType()) + " "
					+ mem.name().replace("_", "\\_") + "(" + getParams(mem).replace("_", "\\_") + ")}: ");
				System.out.println(mem.commentText().replace('\n', ' '));
				if (mem.commentText().length() < 5) {
					System.out.println(" % TODO");
				}
			}
		}
		if (count == 0) {
			System.out.println("\\item (nincs)");
		}
	}

	static private String getTypeName(Type t) {
		String name = t.simpleTypeName();
		String par = "";
		try {
			TypeVariable[] args = t.asClassDoc().typeParameters();
			for (TypeVariable arg : args) {
				if (par.length() > 0) {
					par += ", ";
				}
				par += getTypeName(arg);
			}
		} catch (Exception e) {
			par = "";
		}
		if (par.length() > 0) {
			name += "<" + par + ">";
		}
		name += t.dimension();
		return name;
	}

	private static String getParams(MethodDoc mem) {
		Parameter[] pars = mem.parameters();
		String ret = "";
		for (Parameter p : pars) {
			if (ret.length() > 0) {
				ret += ", ";
			}
			ret += getTypeName(p.type()) + " " + p.name();
		}
		return ret.replace("_", "\\_");
	}

	private static String getParams(ConstructorDoc mem) {
		Parameter[] pars = mem.parameters();
		String ret = "";
		for (Parameter p : pars) {
			if (ret.length() > 0) {
				ret += ", ";
			}
			ret += getTypeName(p.type()) + " " + p.name();
		}
		return ret.replace("_", "\\_");
	}
}
