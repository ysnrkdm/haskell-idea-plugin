package org.jetbrains.cabal.parser

import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.jetbrains.haskell.parser.CabalCompositeElementType
import org.jetbrains.haskell.parser.HaskellToken
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import org.jetbrains.cabal.psi.*

public trait CabalTokelTypes {


    class object {
        val defaultContructor : (ASTNode) -> PsiElement = { node ->
            ASTWrapperPsiElement(node)
        }
        val COLON: IElementType = HaskellToken(":")
        val COMMA: IElementType = HaskellToken(",")
        val COMMENT: IElementType = HaskellToken("COMMENT")
        val LEFT_PAREN: IElementType = HaskellToken("(")
        val RIGHT_PAREN: IElementType = HaskellToken(")")
        val DOT: IElementType = HaskellToken(".")
        val END_OF_LINE_COMMENT: IElementType? = HaskellToken("--")
        val STRING: IElementType = HaskellToken("string")
        val NUMBER: IElementType = HaskellToken("number")
        val ID: IElementType = HaskellToken("id")
        val COMPARATOR   : IElementType = HaskellToken("COMPARATOR")

        val PROPERTY: IElementType = CabalCompositeElementType("PROPERTY", defaultContructor)
        val NAME: IElementType = CabalCompositeElementType("NAME", { Name(it) })
        val FILE_NAME: IElementType = CabalCompositeElementType("FILE_NAME", { FileName(it) })
        val FILE_REF : IElementType = CabalCompositeElementType("FILE_REF" , { FileReference(it) })
        val PROPERTY_KEY: IElementType = CabalCompositeElementType("PROPERTY_KEY", { PropertyKey(it) })
        val PROPERTY_VALUE: IElementType = CabalCompositeElementType("PROPERTY_VALUE" , defaultContructor)
        val EXECUTABLE: IElementType = CabalCompositeElementType("EXECUTABLE", { Executable(it) })
        val TEST_SUITE: IElementType = CabalCompositeElementType("TEST_SUITE", { TestSuite(it) })
        val SECTION: IElementType = CabalCompositeElementType("SECTION", defaultContructor)
        val SECTION_TYPE: IElementType = CabalCompositeElementType("SECTION_TYPE", { SectionType(it) })

        val COMMENTS   : TokenSet = TokenSet.create(END_OF_LINE_COMMENT, COMMENT)
        val WHITESPACES: TokenSet = TokenSet.create(TokenType.WHITE_SPACE)

        val VERSION           : IElementType = CabalCompositeElementType("VERSION_PROPERTY"         , { VersionProperty(it) }         )
        val CABAL_VERSION     : IElementType = CabalCompositeElementType("CABAL_VERSION_PROPERTY"   , { CabalVersionField(it) }       )
        val HOMEPAGE          : IElementType = CabalCompositeElementType("HOMEPAGE"                 , { HomepageField(it) }           )
        val PACKAGE_URL       : IElementType = CabalCompositeElementType("PACKAGE_URL"              , { PackageURLField(it) }         )
        val NAME_FIELD        : IElementType = CabalCompositeElementType("NAME_FIELD"               , { NameField(it) }               )
        val MAIN_FILE         : IElementType = CabalCompositeElementType("MAIN_FILE"                , { MainFile(it) }                )
        val DATA_FILES        : IElementType = CabalCompositeElementType("DATA_FILES"               , { DataFiles(it) }               )
        val EXTRA_SOURCE      : IElementType = CabalCompositeElementType("EXTRA_SOURCE"             , { ExtraSource(it) }             )
        val EXTRA_TMP         : IElementType = CabalCompositeElementType("EXTRA_TMP"                , { ExtraTmp(it) }                )
        val EXTRA_DOC         : IElementType = CabalCompositeElementType("EXTRA_DOC"                , { ExtraDoc(it) }                )

        val BUILD_DEPENDS     : IElementType = CabalCompositeElementType("BUILD_DEPENDS"            , { BuildDependsField(it) }       )

        val SIMPLE_CONSTRAINT : IElementType = CabalCompositeElementType("SIMPLE_VERSION_CONSTRAINT", { SimpleVersionConstraint(it) } )
        val COMPLEX_CONSTRAINT: IElementType = CabalCompositeElementType("COMPLEX_CONSTRAINT"       , { ComplexVersionConstraint(it) })
        val FULL_CONSTRAINT   : IElementType = CabalCompositeElementType("FULL_CONSTRAINT"          , { FullVersionConstraint(it) }   )
        val DEPENDENCY_LIST   : IElementType = CabalCompositeElementType("DEPENDENCY_LIST"          , { DependencyList(it) }          )
        val URL               : IElementType = CabalCompositeElementType("URL"                      , { URL(it) }                     )
    }
}
