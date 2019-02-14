<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
    <tiles:insertDefinition name="header" />
 <body>
        <tiles:insertDefinition name="nav" />
<div class="main-center">
 <center>
  <h2>User's Manual</h2>
 </center>

<ol>        
        <h2><li>Aledania</li></h2>

<p>
Aledania is a web application that implements a hybrid algorithm for the lambda calculus, which computes normal form of lambda terms, using beta reductions and reductions of Broda and Damas' combinators. The use of combinators in this algorithm avoids the problem of variable capture in each reduction.
</p>

        <h2><li>Language to Introduce Lambda Terms to the Application</li></h2>

<p>
The application contains a parse to recognize lambda terms. The language that recognizes this parse is defined with the following productions:
</p>

$$Term\rightarrow var~|~numChurch~|~alias~|~Term~Term~|~\verb|lambda|~Var~.~Term~|~(Term)$$

<p>
Where the token var represents the variables, the token numChurch corresponds to the numerals of Church and the token alias ​​corresponds to abbreviations of a term by means of an Alias. The production whose right side is Term Term, indicates that the functional application is written using the concatenation of the terms, for example, to say that the function Term1 is applied with the argument Term2, is written as Term1 Term2.
</p>

<p>
The association of the functional application between terms is to the left, the association between lambda abstractions is to the right and the functional application has greater precedence than the lambda abstractions.
</p>

    <ol type="A">
        <h3><li>Format to Write Variables</li></h3>

<p>
The format of variables recognized by the parse is as follows: They must start with 'x' (uppercase or lowercase), followed by a natural number without the use of blank spaces. For example x0, x1, x2, X8, X12, X20, x25 are variables.
</p>
        <h3><li>Format for Church's Numerals</li></h3>

<p>
The format of the Church's numerals that recognizes the parse is the following: They must start with 'n' (capital or small), followed by a natural number, without the use of blank spaces. For example n1 is recognized by the parse, as the numeral of Church \(\lambda x1.\lambda x2.x1x2\) or N2 is recognized by the parse, as the numeral of Church \(\lambda x1.\lambda x2.x1(x1x2)\).
</p>

        <h3><li>Availability of Predefined, Private and Public Terms</li></h3>

<p>
To make arithmetic calculations the user has a series of prefabricated lambda terms, these terms are invoked by means of a unique Alias. Among the prefabricated terms, all users have a series of predefined terms of frequent use, for arithmetic calculations or list management. Additionally each user has the freedom to create their own terms and assign them to a unique Alias, which are private within the session of the user. The user has the liberty to publish some of their private prefabricated terms, so that other users can use it.
</p>
        <h3><li>Format to Write Alias</li></h3>

<p>
All Aliases must be written with the symbol '-' (which can not be used as the first character of the Alias) and the letters from 'a' to 'z'. They must start with a capital letter and the other letters must be lowercase, they must end in a letter followed by '_', if the Alias ​​is used to invoke private lambda terms, otherwise it must end in a letter. Each Alias ​​for a private term of a user, must be unique with respect to the alias collection of the private terms of that user. When a user publishes some of their private term, it is published by automatically deleting the suffix '_' from the Alias.
</p>

        <h3><li>Examples of Well Written Terms</li></h3>


The following are examples of lambda well-written terms, which recognizes the parse of the application:
         <ul class="help">
           <li> lambda x0.lambda x1.lambda x2. x0 x2(x1x2)</li>
           <li> lambda x0.x0 Gcd_</li>
           <li> (lambda x0.lambda x1.lambda x2. x0(x1x2)) N4 N3</li>
           <li> (lambda x0.lambda x1.lambda x2.x1(x0x1x2)) n6</li>
         </ul>

   </ol>
        <h2><li>Profile</li></h2>

<p>
The Profile view shows the user's data such as name, last name and email. The user's data can be modified by clicking 'Edit Profile'.
</p>

        <h2><li>Save Term</li></h2>

<p>
The Save Term view is used to enter by means of a form, a term and the associated alias, so that this term is stored as a private prefabricated term and can be invoked through the entered alias. The lambda term to save can be constructed using other prefabricated terms as subterm. To use a prefabricated term as subterm, when writing the term to be saved in the form, the alias of the prefabricated term must be written in the position that you want to place as a subterm.
</p>

<p>
To facilitate the use of Alias ​​in the writing of the term to be saved, the lists of predefined, private and public terms are available at the bottom of the form. By clicking on an element in the list in the Alias ​​column, the Alias ​​corresponding to the element is printed in the place where the cursor is located or where the text is underlined.
</p>

<p>
It is not allowed to save a term that is alpha equivalent to another Predefined, Public or Private term of the user, nor to associate an Alias ​​that has already been used in another Predefined, Public or Private term.
</p>

<p>
The Alias ​​to enter in the form must start with a capital letter, continue with lowercase or the symbol '-' and end with a lowercase letter followed by the symbol '_'.
</p>
        <h2><li>List My Terms</li></h2>

<p>
In the List My Terms view, there is a table with all the private terms saved by the user, for each term in the table you have the options of Modify the term by another lambda term, Modify Aliases, Delete and Publish.
</p>
   <ol type="A">
        <h3><li>Modification of Terms that Have Already Been Used</li></h3>

<p>
If a term is modified, deleted or modified its Alias, but had previously been used, as a subterm of another prefabricated term that is still in the database, then this subterm maintains the original version (pre-elimination) of it.
</p>

        <h3><li>Publish</li></h3>

<p>
When selecting the option Publish in one of the private terms of the user, the term is made available to the rest of the users with the same alias ​​that had as private term, but deleting the terminal symbol '_' of the Alias. It is not allowed to publish a term that is alpha equivalent to a predefined or public term, or that your alias ​​without the suffix '_', has the same alias ​​to a predefined or public term.
</p>

   </ol>

        <h2><li>My Publications</li></h2>

<p>
The My Publications view shows a table with all the terms published by the user. For each term published, you have the option Delete.
</p>

<p>
Once the term is eliminated by the user, the other users will not be able to visualize that term. However, if any user uses it as a subterm of an own term, that subterm maintains the original version (pre-elimination) of it.
</p>

        <h2><li>List Public Terms</li></h2>

<p>
The List Public Terms view, shows a table with all the terms published by all users
</p>

        <h2><li>Computing</li></h2>

<p>
The Computing view contains a form where you can enter a lambda term, to compute its normal form by means of a hybrid algorithm, that uses beta reductions and reductions of Broda and Damas' combinators. To prevent the server computing indefinitely, you must set the maximum number of reductions, that the application will perform to calculate the normal form of any term.
</p>

        <h2><li>Tools for Inspect the Process of Normalization</li></h2>

<p>
Once entered a term to compute its normal form, the application will show a view that has the controls to handle a debugger, a graph that represents the execution and a comparative table with the data of number of translations and reductions made during the execution.
</p>
           <ol type="A">
             <h3><li>Debugger</li></h3>

<p>
After entering a term to compute its normal form, it is possible to inspect the execution step by step, the operations that have the debugger are: Next, Back, Next Beta, Back Beta, Next Translate, Back Translate, nNext, nBack, nNext Beta , nBack Beta, nNext Translate, nBack Translate.
</p>
               <ol type="i">
                 <h4><li>Next, nNext, Back and nBack</li></h4>

<p>
The operation Next of the Debuger allows visualizing the next term of the execution, starting from a certain state, that is, the result of reducing the normal redex of combinator type or translation type, the result of applying a Bracket Abstraction or a textual substitution to the redex of lambda type. Textual substitutions and combinator type reductions are denoted by \(\rightarrow\), translation type reductions are denoted by \(\uparrow\) and the application of a Bracket Abstraction is denoted by \(\downarrow\).
</p>

<p>
The operation nNext has a form to enter a natural number \(n\), this operation is equivalent to applying Next \(n\) times.
</p>

<p>
The operations Back and nBack are analogous to Next and nNext, only that instead of calculating the following terms of the execution, calculate the previous ones.
</p>
                 <h4><li>Next Beta, nNext Beta, Back Beta and nBack Beta</li></h4>

<p>
The operation Next Beta of the Debugger allows, starting from a certain state, to visualize the next term of the execution, resulting from a textual substitution or to reduce a redex of the combinator type.
</p>

<p>
The operation nNext Beta has a form to enter a natural number \(n\), this operation is equivalent to applying nNext Beta \(n\) times.
</p>

<p>
The Beta and nBack Beta operations are analogous to Next Beta and nNext Beta, only that instead of calculating the following terms of the execution, calculate the previous ones.
</p>
                 <h4><li>Next Translate, nNext Translate, Back Translate and nBack Translate</li></h4>

<p>
The operation Next Translate of the Debugger allows, starting from a certain state, to visualize the next term of the execution, resulting from applying a Bracket Abstraction or reducing a translation type redex.
</p>

<p>
The operation nNext Translate has a form to enter a natural number \(n\), this operation is equivalent to applying nNext Translate \(n\) times.
</p>

<p>
The operations Back Translate and nBack Translate are analogous to Next Translate and nNext Translate, only that instead of calculating the following terms of the execution, calculate the previous ones.
</p>
               </ol>

              <h3><li>Execution Chart</li></h3>

<p>
To represent an execution of the algorithm, a diagram is used as in the following example
</p>
           </ol>
</ol>

$$\begin{array}{ccccccc}
  (\lambda x_3.\lambda x_1.(\lambda x_0.x_0x_3)(\lambda x_4.x_1))(\lambda x_2.x_1) & \longrightarrow_{\beta} & \lambda x_5.(\lambda x_0.x_0(\lambda x_2.x_1))(\lambda x_4.x_5) & \longrightarrow_{\beta} & \lambda x_5.(\lambda x_4.x_5)(\lambda x_2.x_1) & \longrightarrow_{\beta} & \lambda x_5.x_5 \\
   \downarrow <> & ~ & ~ & ~ & ~ & ~ & ~\\
  (\lambda x_3.(\lambda x_1.((\Phi _{c}x_3)(\Phi_Kx_1))))(\lambda x_2.x_1) & ~ & \lambda x_2.\Phi _{c}(\lambda x_2.x_1)(\Phi_K x_2) & \longrightarrow & \lambda x_2.\Phi_K x_2(\lambda x_2.x_1) & \longrightarrow & \lambda x_2.x_2 \\
  \downarrow <> & ~ & \uparrow <>^{-1} & ~ & ~ & ~ & ~ \\
  ( \lambda x_3.\Phi _{bb}(\Phi _{c}x_3)\Phi_K)(\lambda x_2.x_1) & \longrightarrow & \Phi _{bb}(\Phi _{c}(\lambda x_2.x_1))\Phi_K & ~ & ~ & ~ & ~
\end{array}$$

<ol>
           <ol type="A">
<p>
The digrama consists of two dimensions. The y-axis corresponds to the application of Bracket Abstraction to compute \(<>\) and reductions of redex of the translation type to compute \(<>^{-1}\). The x-axis corresponds to reductions of the combinator type or textual substitutions, to carry out reductions of lambda type redex. The first row of the diagram corresponds to lambda terms and usual beta reductions of the lambda calculus. In this way, every two consecutive columns, can be seen as an equivalence class of translatable terms between them by means of \(<>\) and \(<>^{-1}\), and where the reduction of type combinator or type lambda, captured between the two columns, corresponds to the usual beta reduction of the lambda calculus, expressed in the first row between those two columns.
</p>

<p>
This type of diagrams are similar to an electrocardiogram, with the intention that you can observe how much zigzag, and so you can make a comparison of percentage of operations of direct and inverse translation vs the reductions, that correspond to the beta reductions of classic lambda calculus
</p>

<p>
Since the written terms can take up a lot of space, the application presents diagrams like the previous ones, using only points in the Cartesian plane. For example, for the previous example, the application shows the following diagram.
</p>

$$\begin{array}{ccccccc}
  \square~~~~~ & \longrightarrow_{\beta} & \square~~~~~~~~~ & \longrightarrow_{\beta} & \square & \longrightarrow_{\beta} & \square \\
   \downarrow <> & ~ & ~ & ~ & ~ & ~ & ~\\
  \square~~~~~ & ~ & \square~~~~~~~~~ & \longrightarrow & \square & \longrightarrow & \square \\
  \downarrow <> & ~ & \uparrow <>^{-1} & ~ & ~ & ~ & ~ \\
  \square~~~~~ & \longrightarrow & \square~~~~~~~~~ & ~ & ~ & ~ & ~
\end{array}$$

<p>
However, by clicking on one of the rectangles, you can see the written term, which corresponds to that position
</p>
           </ol>


<br>
<br>
<br>
</ol>
</div>
        <footer>
         <div class="row footer-div">
          <span class="footer-login-span" >
           federico
          </span>
         </div>
        </footer>
</div>
 </body>
</html>
