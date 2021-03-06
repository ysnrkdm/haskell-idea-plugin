package org.jetbrains.cabal.psi

import org.jetbrains.cabal.psi.PathsField
import org.jetbrains.cabal.parser.CabalTokelTypes
import com.intellij.lang.ASTNode
import com.intellij.openapi.vfs.VirtualFile
import java.io.File
import java.util.ArrayList

public class IncludesField(node: ASTNode) : PathsField(node) {

    public override fun getParentDirs(prefixPath: Path, originalRootDir: VirtualFile): List<VirtualFile> {
        if (prefixPath.isAbsolute()) return listOf()
        var res = ArrayList<VirtualFile>()
        res.addAll(getParentDirsFromSourceDirs(prefixPath, originalRootDir, { getIncludeDirs() }))
        return res
    }
}