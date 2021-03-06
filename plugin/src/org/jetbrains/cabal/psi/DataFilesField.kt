package org.jetbrains.cabal.psi

import com.intellij.lang.ASTNode
import org.jetbrains.cabal.psi.PropertyField
import org.jetbrains.cabal.psi.PathsField
import com.intellij.openapi.vfs.VirtualFile
import java.io.File

public class DataFilesField(node: ASTNode) : PathsField(node) {

    public override fun getParentDirs(prefixPath: Path, originalRootDir: VirtualFile): List<VirtualFile> {
        val dirPath = File(prefixPath.getPathWithParent(prefixPath.getCabalFile().getActualDataDir())).getParent()
        val dirFile = if (dirPath == null) null else originalRootDir.getFileSystem().findFileByPath(dirPath)
        if (dirFile == null) return listOf()
        return listOf(dirFile)
    }
}