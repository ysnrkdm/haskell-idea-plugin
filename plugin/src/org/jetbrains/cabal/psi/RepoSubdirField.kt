package org.jetbrains.cabal.psi

import com.intellij.lang.ASTNode
import org.jetbrains.cabal.psi.PropertyField
import org.jetbrains.cabal.psi.PathsField
import com.intellij.openapi.vfs.VirtualFile
import java.util.ArrayList
import java.io.File

public class RepoSubdirField(node: ASTNode) : PathsField(node) {

    public override fun getNextAvailableFile(prefixPath: Path, originalRootDir: VirtualFile): List<String> = listOf()

    public override fun getParentDirs(prefixPath: Path, originalRootDir: VirtualFile): List<VirtualFile> = listOf()
}