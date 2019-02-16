<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
    <tiles:insertDefinition name="header" />
 <body>
        <c:choose>
            <c:when test="${sesion.equals(logout)}">
      <div class="container-fluid">
        <nav class="row navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#options">
                <span class="navbar-toggler-icon"></span>    
            </button>
          <div class="collapse navbar-collapse" id="options">
          <ul class="navbar-nav">
                <li class="nav-item active" ><a href="theo" class="nav-link">Theoretical Basis</a></li>
                <li class="nav-item" ><a href=".." class="nav-link">Log In</a></li>
          </ul>
          </div>
        </nav>
            </c:when>
            <c:otherwise>
              <tiles:insertDefinition name="nav" />
            </c:otherwise>
        </c:choose>
<div class="main-center">

        <center>
          <h2>Normalization Algorithm of Lambda Calculus Based on Bijective Properties of Broda-Damas' Bracket Abstraction</h2> <table>
<tr>
 <td>Federico Flaviani&nbsp;&nbsp;&nbsp;</td><td>Asc&aacute;nder Su&aacute;rez&nbsp;&nbsp;&nbsp;</td><td>Elias Tahhan</td>
</tr>
<tr>
 <td>fflaviani@usb.ve&nbsp;&nbsp;&nbsp;</td><td>ascander@usb.ve&nbsp;&nbsp;&nbsp;</td><td>etahhan@usb.ve</td>
</tr>
</table>
        </center>

<ol>        

        <h2><li>Broda-Damas' Combinators</li></h2>

<p>
A Broda-Damas combiner is a constant of the form \(\Phi_K\) or \(\Phi_{\alpha}\), where \(\alpha\) is a list based on the symbols \(c, b\) and parentheses, called the combinatorial index. The set of combinatorial indices is recursively defined as:
</p>
          <ul class="help">
            <li>The empty list is a combinatorial index</li>
            <li>If \(\alpha\) is a combinatorial index, then \(c\alpha\) and \(b\alpha\) are combinatorial indexes</li>
            <li>If \(\alpha_1,\alpha_2\) are combinatorial indexes, then \((\alpha_1,\alpha_2)\) is a combinatorial index</li>
          </ul>

<p>
A combinatorial term with variables is a term that is constructed using variables and combinators, with the functional application, as the only operator for the formation of terms.
</p>

<p>
It is denoted as \(\\#\alpha\) the number of symbols \(a\) and \(b\) that the \(\alpha\) list has. Also it is defined \(\\#K:=1\)
</p>
          <ol type="A">
            <h3><li>Rewrite Rule for Broda-Damas' Combinators</li></h3>

<p>
If \(\Phi_{\alpha}\) is a combinator with \(p_1,p_2,\dots,p_n,p_{n+1}\) terms and \(\\#\alpha=n\), then the term \(\Phi_{\alpha}p_1p_2\dots p_np_{n+1}\) is called redex and it's defined the rewriting rule $$\Phi_{\alpha}p_1p_2\dots p_np_{n+1}\rightarrow\kappa(\Phi_{\alpha}p_1p_2\dots p_np_{n+1})$$ Where the kappa syntactic function is recursively defined as follows:
</p>

              <ul class="help">
                 <li>\(\kappa(\Phi_K pq)=p\)</li>
                 <li>\(\kappa(\Phi p)=p\)</li>
                 <li>\(\kappa(\Phi_{c\alpha} p_1p_2\dots p_{\\#\alpha+1})=(\kappa(\Phi_{\alpha}p_2\dots p_{\\#\alpha +1}))p_1\)</li>
                 <li>\(\kappa(\Phi_{b\alpha} p_1p_2\dots p_{\\#\alpha+1})=p_1(\kappa(\Phi_{\alpha}p_2\dots p_{\\#\alpha +1}))\)</li>
                 <li>\(\kappa(\Phi_{(\alpha_1,\alpha_2)} p_1\dots p_{\\#\alpha_1}q_1\dots q_{\\#\alpha_2}t)=(\kappa(\Phi_{\alpha_1}q_1\dots q_{\\#\alpha_1}t))(\kappa(\Phi_{\alpha_2}q_1\dots q_{\\#\alpha_2}t))\)</li>
              </ul>
          </ol>

        <h2><li>Bracket Abstraction</li></h2>

<p>
For a variable \(x\) the Bracket Abstraction \([x]\) is a syntactic function that applies over a combinatorial term \(p\) with variables, with the following property $$([x]p)q\rightarrow p[x:=q].$$. Where \([x:=q]\) denotes the alpha substitution operator. Th alpha substitution renames the bound variables that are necessary, to avoid variable capture.
</p>

<p>
In the case of combinatorial terms built with Broda-Damas' combinators, a function that satisfies the previous property can be defined as follows:
</p>

<p>
If you define the functions \(t_i\) as:</p>

\(t_1(q,r_1)=\left\{\begin{array}{lcc}
                               \Phi_{c\alpha}qw_1\dots w_{\\#\alpha} & if & r_1=\Phi_{\alpha}w_1\dots w_{\\#\alpha} \\
                               \Phi &    & otherwise
                               \end{array}
                       \right.\) <br>

\(t_2(p,r_2)=\left\{\begin{array}{lcc}
                               \Phi_{b\alpha}pw_1\dots w_{\\#\alpha} & if & r_2=\Phi_{\alpha}w_1\dots w_{\\#\alpha} \\
                               \Phi &    & otherwise
                               \end{array}
                       \right.\) <br>

\(t_3(r_1,r_2)=\left\{\begin{array}{lcc}
                               \Phi_{(\alpha_1,\alpha_2)}w_1\dots w_{\\#\alpha_1} w'_1\dots w'_{\\#\alpha_2} & if & r_1=\Phi_{\alpha_1}w_1\dots w_{\\#\alpha_1}~and~r_2=\Phi_{\alpha_2}w'_1\dots w'_{\\#\alpha_2} \\
                               \Phi &    & otherwise
                               \end{array}
                       \right.\)

<p>
then the Bracket Abstraction \([x]\) is defined recursively as:
</p>
          <ul class="help">
            <li>\([x] p=\Phi\) if \(x=p\),</li>
            <li>\([x] p=\Phi_K p\) if \(x\notin p\),</li>
            <li>\([x] pq = t_1(q,[x]p)\) if \(x\in p \wedge x\notin q\),</li>
            <li>\([x] pq = t_2(p,[x]q)\) if \(x\notin p \wedge x\in q\) and</li>
            <li>\([x] pq = t_3([x]p,[x]q)\) if \(x\in p \wedge x\in q\).</li>
          </ul>

<p>
This Bracket Abstraction has the property that for any combinatorial term \(p\), the term \([x]p\) is always of the \(\Phi_{\alpha}p_1p_2\dots p_{\\#\alpha}\) form.
</p>
          <ol type="A">
            <h3><li>Examples of Bracket Abstraction Application</li></h3>

              <ul class="help">
                <li>\([x]x=\Phi\)</li>
                <li>\([x]xx=t_3([x]x,[x]x)=t_3(\Phi,\Phi)=\Phi_{(,)}\)</li>
                <li>\([y]\Phi_{(,)}y=t_2(\Phi_{(,)},[y]y)=t_2(\Phi_{(,)},\Phi)=\Phi_{b}\Phi_{(,)}=\Phi_b\Phi_{(,)}\)</li>
                <li>\([y]\Phi_{(,)}y z=t_1(z,[y]\Phi_{(,)}y)=t_1(z,\Phi_b\Phi_{(,)})=\Phi_{cb}z\Phi_{(,)}\)</li>
              </ul>

          </ol>

        <h2><li>Translation of Lambda Terms to Combinatorial Terms</li></h2>

<p>
A lambda term can be translated into a combinatorial term, using the translation function \(<>\), which is computed by applying a Bracket Abstraction \([x]\) in the place of each lambda abstraction \(\lambda x\), which has the lambda term. A recursive definition of \(<>\) is the following.
</p>

          <ul class="help">
            <li>If \(x\) is a variable then \(<\hspace{0cm}x>=x\)</li>
            <li>\(<\hspace{0cm}pq>=<\hspace{0cm}p><\hspace{0cm}q>\)</li>
            <li>\(<\lambda x.p>=[x]<\hspace{0cm}p>\)</li>
          </ul>

<p>
If \(\langle x:=p\rangle\) is denoted as the simple substitution operator (without renaming bound variables), then translation \(<>\) satisfies the following two properties:
</p>

          <ul class="help">
            <li>\(<\hspace{0cm}q[x:=t]>=<\hspace{0cm}q>\langle x:=<\hspace{0cm}t>\rangle\) that is, the translation of a substitution can be carried out, translating the two terms involved in the substitution and then making a simple substitution.</li>
            <li>If \(<\hspace{0cm}p>\rightarrow q\), then there is lambda term \(p'\) such that \(p\rightarrow_{\beta} p'\) and \(<\hspace{0cm}p'>=q\)</li>
          </ul>

          <ol type="A">
            <h3><li>Hidden Redexes</li></h3>

<p>
The translation \(<>\) does not preserve redex, meaning that if the lambda term \(p\) contains \(n\) redex, then the combinatorial term \(<\hspace{0cm}p>\) has potentially less than \(n\) redex of Broda and Damas. This usually happens when there is a redex in \(p\), which is under the scope of a \(\lambda\) abstraction, generally these redex do not become a redex of Broda and Damas in \(<\hspace{0cm}p>\). These redex that disappear, they are called hidden redex.
</p>

<p>
For example \(\lambda x_0.(\lambda x_1.x_0) x_2\) is translated into \([x_0]([x_1]x_0) x_2\) that is equal to \([x_0]\Phi_K x_0 x_2\), that although \(\Phi_K x_0x_2\) is a redex, when applying the Abstraction Bracket \([x_0]\) this redex hides since the resulting term is \(\Phi_{cb}x_2\Phi_K\), that does not contain any redex. One way to find the hidden redex, is to go back the operation of Bracket Abstraction in a subterm that is the image of a Bracket Abstraction, which, as stated above, is always of the \(\Phi_{\alpha}p_1p_2\dots p_{\\#\alpha}\) form.
</p>

            <h3><li>Inverse of the Translation of Lambda Terms to Combinatorial Terms</li></h3>

<p>
The Bracket Abstraction defined above, makes the function \(<>\) be injective in \(\Lambda\), therefore \(<>\) is bijective on its image and the inverse \(<>^{-1}\), is well defined in the image of \(<>\). An algorithm to compute this inverse is defined recursively below:
</p>

          <ul class="help">
            <li>\(<\hspace{0cm}p>^{-1}=\lambda x_c.<\kappa(px_c)>^{-1}\) if \(p\) is of the form \(\Phi_{\alpha}p_1\dots p_{\\#\alpha}\) and where \(x_c\) is an indexed variable with \(c=max(FreeIndexes(p))+1\) and \(FreeIndexes(p)\) is the set of indices of the free variables of \(p\)</li>
            <li>\(<\hspace{0cm}p>^{-1}=p\) if \(p\) is a variable or constant</li>
            <li>\(<\hspace{0cm}pq>^{-1}=<\hspace{0cm}p>^{-1}<\hspace{0cm}q>^{-1}\)</li>
          </ul>

<p>
If there is ambiguity about which rule to take, the one listed first is used.
</p>

          </ol>

        <h2><li>Variable Capture</li></h2>

<p>
The following is a beta reduction $$(\lambda x_3.\lambda x_1.(\lambda x_0.x_0x_3)(\lambda x_4.x_1))(\lambda x_2.x_1)\rightarrow_{\beta} (\lambda x_1.(\lambda x_0.x_0x_3)(\lambda x_4.x_1))[x3:=\lambda x_2.x_1].$$ The contractum of this reduction is: $$\lambda \underline{x_5}.(\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.\underline{x_5}).$$ The variable \(x_5\) of the contractum is underlined, to emphasize that this variable is different from the one that was in the same position in the redex, that is, there was a renaming of the variable to be able to carry out the reduction.
</p>

<p>
Sometimes in the lambda calculus it is inevitable to rename the variables to make the reductions, so there are algorithms that compute the normal form of the lambda terms, dealing with this problem in different ways. There are two famous ways to attack the problem: one way is to use the representation of lambda terms with De Bruijn indexes, and the other is to use explicit substitution like Abadi-Cardinelli-Curien-Levy style.
</p>

<p>
Aledania implements an alternative hybrid strategy, which uses beta reductions and reductions of Broda and Damas.
</p>

          <ol type="A">
            <h3><li>Alternative Hybrid Strategy</li></h3>

<p>
In the following diagram each term that is in the same column are the same, but in different stages of the application of \(<>\), so that the one that is above is a pure lambda-term and down are the different stages of translation, that are carried out from the inside to the outside of the term. Each column should be understood as an equivalence class. The consecutive columns represent the before and after making a reduction, so that the terms of the column on the right is the class resulting from making a reduction, by properties of \(<>\), the resulting class is the same no matter if a beta reduction is made to the redex (\R\), or reduction of Broda and Damas is made to the translation of the redex (\R\).
</p>

$$\begin{array}{ccc}
  (\lambda x_3.\lambda x_1.(\lambda x_0.x_0x_3)(\lambda x_4.x_1))(\lambda x_2.x_1) & \longrightarrow_{\beta} & \lambda \underline{x_5}.(\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.\underline{x_5}) \\
   \downarrow <> & ~ & ~ \\
  ([x_3]([x_1]((\Phi _{c}x_3)(\Phi_Kx_1))))([x_2]x_1) & ~ & \downarrow <> \\
  \downarrow <> & ~ & ~ \\
  ( [x_3]\Phi _{bb}(\Phi _{c}x_3)\Phi_K)([x_2]x_1) & \longrightarrow & \Phi _{bb}(\Phi _{c}([x_2]x_1))\Phi_K
\end{array}$$

<p>
In the previous diagram you can see in the upper part two lambda-terms, where the one on the left is reduced to the one on the right and to make this reduction a renaming of the variable \(x_1\) to \(x_5\) had to be done. However, there is another way to avoid renaming, this path is make a translation to combinators, the subterm that is under the scope of the abstraction \(\lambda x_3\), so that bounded variables disappear and alpha substitution can be done with simple substitution. The term \(\Phi _{bb}(\Phi _{c}( [x_2]x_1))\Phi_K\) is the translation under \(<>\) of \(\lambda x_5.(\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.x_5)\) and as the substitution commutes with \(<>\), then</p> $$<\lambda x_5.(\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.x_5)>$$ $$=<\lambda x_1.(\lambda x_0.x_0x_3)(\lambda x_4.x_1)[x_3:=\lambda x_2.x_1]>$$ $$=<\lambda x_1.(\lambda x_0.x_0x_3)(\lambda x_4.x_1)>\langle x3:=<\lambda x_2.x_1>\rangle$$
$$=\Phi _{bb}(\Phi _{c}x_3)\Phi_K\langle x_3:=[x_2]x_1\rangle=\Phi _{bb}(\Phi _{c}([x_2]x_1))\Phi_K.$$ 

<p>
Note that the term \(\Phi _{bb}(\Phi _{c}( [x_2]x_1))\Phi_K\) was obtained by making a simple substitution \(\langle x_3:=[x_2]x_1\rangle\) to the term \(\Phi _{bb}(\Phi _{c}x_3)\Phi_K\), thus avoiding rename variables.
</p>

<p>
On the other hand note that the term \(\Phi _{bb}(\Phi _{c}( [x_2]x_1))\Phi_K\) does not have redex, however its corresponding lambda term in the diagram \(\lambda x_5.(\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.x_5)\) has a redex \((\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.x_5)\). This happens because sometimes the translation \(<>\) disappears redex, with which there is no way to continue reducing in this way. However, since the translation \(<>\) has inverse \(<>^{-1}\), it is possible to recover part of the original lambda-term, to the point of discovering the redex that \(<>\) hid and continue reducing. This idea is exemplified in the following figure.
</p>

$$\begin{array}{ccccc}
   \lambda x_5.(\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.x_5) & \longrightarrow_{\beta} & \lambda x_5.(\lambda x_4.x_5)(\lambda x_2.x_1) & \longrightarrow_{\beta} & \lambda x_5.x_5 \\
   ~  & ~ & ~ & ~ & ~ \\
  [\underline{x_2}]\Phi _{c}([x_2]x_1)(\Phi_K\underline{x_2}) & \longrightarrow & [\underline{x_2}]\Phi_K\underline{x_2}([x_2]x_1) & \longrightarrow & [x_2]x_2 \\
  \uparrow <>^{-1} & ~ & ~ & ~ & ~\\
  \Phi _{bb}(\Phi _{c}([x_2]x_1))\Phi_K & ~ & ~ & ~ & ~
\end{array}$$

<p>
The above diagram is a continuation of the first. There it can be seen that the term \(\Phi _{bb}(\Phi _{c}([x_2]x_1))\Phi_K\) is in normal form. Since the translation process is done from the inside to the outside of the term, then a preimage can be calculated by applying the inverse translation \(<>^{-1}\) from the outside inwards. This inverse process of translation was partially done, so that the term that was obtained still has some combinators inside it, this is because it is not necessary to translate it to a pure lambda-term, but until the point where a redex is discovered. In this case the partial preimage \([\underline{x_2}]\Phi _{c}([x_2]x_1)(\Phi_K\underline{x_2})\) was obtained and \(x_2\) is emphasized to indicate that it was the fresh variable that calculates the algorithm that computes the inverse translation \(<>^{-1}\), in addition \(\Phi _{c}([x_2]x_1)(\Phi_K\underline{x_2})\) is a redex that did not exist before and that corresponds to the redex \((\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.x_5)\) of  superior left lambda term. This redex can be reduced with the rewriting rules of Broda and Damas, and since it is known that the translation \(<>\) commutes with the reduction of Broda and Damas, then the resulting reduced term \([x_2].\Phi_Kx_2([x_2]x_1)\) corresponds to the term \(\lambda x_5.(\lambda x_4.x_5)(\lambda x_2.x_1)\), resulting from reduce in \(\Lambda\) the lambda-term of the upper left.
</p>

<p>
Similarly the term \([x_2]\Phi_Kx_2([x_2]x_1)\) is reduced in combiners to \([x_2]x_2\) and since the reduction of Broda and Damas commutes with \(<>\), then the reduction \([x_2]\Phi_Kx_2([x_2]x_1)\rightarrow [x_2]x_2\) corresponds to the reduction \(\lambda x_5.(\lambda x_4.x_5)(\lambda x_2.x_1)\rightarrow \lambda x_5.x_5\), in addition the preimage of \([x_2]x_2\) under \(<>\) is \(\lambda x_2.x_2\), which is alpha equivalent to \(\lambda x_5.x_5\), so they are the same term.
</p>

<p>
In this way the term \((\lambda x_3.\lambda x_1.(\lambda x_0.x_0x_3)(\lambda x_4.x_1))(\lambda x_2.x_1)\) has been reduced to its normal form, using the help of Broda-Damas' combinators.
</p>

            <h3><li>Hybrid Algorithm</li></h3>

<p>
Aledania implements the hybrid strategy described in the previous section. However, to formalize the strategy as a rewriting system, the following three types of redex are defined.
</p>

              <ul class="help">
                <li>A term of the form \((\lambda x.p)q\) is called a lambda type redex. This type of redex is reduced to the term \(<\hspace{0cm}p>\langle x:= q\rangle\) and a reduction of this type is denoted by \(\rightarrow_{\lambda}\)</li>
                <li>A term of the form \(\Phi_{\alpha}p_1\dots p_{\\#\alpha+1}\) is called a combinator type redex. This type of redex is reduced to the term \(\kappa(\Phi_{\alpha}p_1\dots p_{\\#\alpha+1})\) and a reduction of this type is denoted by \(\rightarrow_{comb}\)</li>
                <li>A term of form \(\Phi_{\alpha}p_1\dots p_{\\#\alpha}\) is called translation type redex. This type of redex is reduced to the term \(\lambda x_c.\kappa(\Phi_{\alpha}p_1\dots p_{\\#\alpha}x_c)\) where \(x_c\) is a variable indexed with \(c=max(FreeIndexes(\Phi_{\alpha}p_1\dots p_{\\#\alpha}))+1\)</li>

<p>
According to this definition, the redex of combinator type \(\Phi_{\alpha}p_1\dots p_{\\#\alpha}p_{\\#\alpha+1}\) always contains a redex of translation type \(\Phi_{\alpha}p_1\dots p_{\\#\alpha}\). To avoid this, it is considered that a redex of translation type, which is within a redex of the combinator type, is not a redex.
</p>
              </ul>
<p>
With this definition of redex, apply the strategy of normal reduction (use the relation \(\rightarrow^{nor}\) consecutively, that is to say always reduce the redex that is more to the left), it is equivalent to execute the hybrid strategy of the previous section, and a normal redex reductions of Combinator type or lambda type, corresponds to a normal beta reductions of the classic lambda calculus. The previus statement it's  shown in the following theorem:
</p>

<p>
<strong>Theorem:</strong> Let be $$QuasiImg(<>):=\{t\in~mixed-\lambda-term|<\hspace{0cm}t>^{-1}~is~pure~\lambda-term\},$$ if \(t\in QuasiImg(<>)\) and \(t\rightarrow^{nor}_{comb,\lambda}t'\), then \(t'\in QuasiImg(<>)\) and \(<\hspace{0cm}t>^{-1}\rightarrow_{\beta}^{nor}<\hspace{0cm}t'>^{-1}\)
</p>

<p>
Which brings as a consequence the following corollary
</p>

<p>
<strong>Corollary:</strong> If \(t\) is a lambda normalizing term in the lambda calculus, with normal form \(p\), then the normal reduction strategy (using redex of types translation, combinator and lambda) applied to \(t\), ends in a term alpha equivalent to \(p\).
</p>
          </ol>

<br>
<br>
<br>
</ol>
</div>
   <c:choose>
    <c:when test="${!sesion.equals(logout)}">
        <footer>
         <div class="row footer-div">
          <span class="footer-login-span" >
           ${username}
          </span>
         </div>
        </footer>
    </c:when>
   </c:choose>
</div>
 </body>
</html>
