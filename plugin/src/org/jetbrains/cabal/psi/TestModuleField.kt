package org.jetbrains.cabal.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import org.jetbrains.cabal.parser.Field
import org.jetbrains.cabal.parser.Disallowedable
import com.intellij.psi.PsiElement

public class TestModuleField(node: ASTNode) : ASTWrapperPsiElement(node), Field, Disallowedable {

    public override fun isEnabled(): String? {
        val parent = (this : PsiElement).getParent()!!
        if (parent is TestSuite) {
            val sectType = parent.getFieldValue("type")
            if ((sectType == null) || (sectType == "detailed-1.0")) return null
            return "test-module field disallowed with such test suit type"
        }
        return null
    }

//    public override fun isEnabled(): String? {
//        val parent = (this : PsiElement).getParent()!! as SourceRepo
//        val repoType = parent.getRepoType()
//        if ((repoType == null) || (repoType == "cvs")) return null
//        return "module field disallowed with cvs repository type"
//    }
}