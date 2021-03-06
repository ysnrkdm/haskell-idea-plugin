package org.jetbrains.haskell.parser.rules

import com.intellij.psi.tree.IElementType
import org.jetbrains.haskell.HaskellLanguage
import org.jetbrains.annotations.NonNls
import com.intellij.psi.PsiElement
import com.intellij.lang.ASTNode
import org.jetbrains.haskell.parser.rules.Rule
import com.intellij.lang.PsiBuilder
import org.jetbrains.haskell.parser.rules.lazy
import org.jetbrains.haskell.parser.rules.rule
import org.jetbrains.haskell.parser.HaskellCompositeElementType

public class RuleBasedElementType(
        debugName: String,
        creator: (ASTNode)->PsiElement,
        ruleCreator : () -> Rule) : HaskellCompositeElementType(debugName, creator), Rule {

    val ruleInternal : Rule = rule(this, ruleCreator);

    override fun parse(state: ParserState) = ruleInternal.parse(state)
}
