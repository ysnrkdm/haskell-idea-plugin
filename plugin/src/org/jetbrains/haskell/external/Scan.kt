package org.jetbrains.haskell.external

import com.intellij.openapi.project.Project
import org.jetbrains.haskell.config.HaskellSettings
import com.intellij.openapi.components.ProjectComponent
import java.io.InputStreamReader
import java.util.ArrayList
import java.io.BufferedReader

/**
 * Created by yoshinori on 10/4/14.
 */

public class Scan(val project: Project, val settings: HaskellSettings) : ProjectComponent {

    override fun projectOpened() {

    }

    fun getPath(): String {
        return settings.getState().scanPath!!
    }

    override fun projectClosed() {

    }

    override fun initComponent() {

    }

    override fun disposeComponent() {

    }

    override fun getComponentName(): String = "scan"

    fun runCommand(fileName: String): List<String> {
        var pb = ProcessBuilder();
        pb.command(getPath(), fileName);
        var process = pb.start();

        val input = InputStreamReader(process.getInputStream()!!)

        val lines = ArrayList<String>()

        var br = BufferedReader(input)
        try {
            while (true) {
                var line = br.readLine()
                if (line == null) break
                lines.add(line!!)
            }
        } finally {
            br.close();
        }

        return lines
    }

}