package org.jetbrains.cabal

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.CachedValuesManager
import com.intellij.psi.util.CachedValueProvider
import java.util.ArrayList
import org.json.simple.JSONArray
import org.json.simple.JSONObject
import org.jetbrains.cabal.psi.*
import com.intellij.psi.PsiElement
import com.intellij.openapi.vfs.VirtualFile
import java.io.File


public class CabalFile(provider: FileViewProvider) : PsiFileBase(provider, CabalLanguage.INSTANCE), FieldContainer {

    public override fun getFileType(): FileType {
        return CabalFileType.INSTANCE
    }
    public override fun accept(visitor: PsiElementVisitor): Unit {
        visitor.visitFile(this)
    }

    public fun getExecutables(): MutableList<Executable> {
        return PsiTreeUtil.getChildrenOfTypeAsList(this, javaClass<Executable>())
    }

    public fun getFlagNames(): List<String> {
        val flags = PsiTreeUtil.getChildrenOfTypeAsList(this, javaClass<Flag>())
        var res: ArrayList<String> = ArrayList()
        for (flag in flags) {
            res.add(flag.getFlagName())
        }
        return res
    }

    public fun getDataDir(): Path? = getField(javaClass<DataDirField>())?.getValue() as Path?

    public fun getActualDataDir(): VirtualFile {
        val dataDir = getDataDir()?.getFileFromRoot()
        if (dataDir == null) return getCabalVirtualFile()!!.getParent()!!
        return dataDir
    }
}
