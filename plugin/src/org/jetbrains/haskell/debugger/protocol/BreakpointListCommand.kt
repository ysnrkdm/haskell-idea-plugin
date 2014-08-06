package org.jetbrains.haskell.debugger.protocol

import org.jetbrains.haskell.debugger.parser.ParseResult
import java.util.Deque
import org.json.simple.JSONObject
import org.apache.commons.lang.NotImplementedException
import org.jetbrains.haskell.debugger.parser.FilePositionList
import org.jetbrains.haskell.debugger.utils.SyncObject
import java.util.ArrayList
import org.jetbrains.haskell.debugger.parser.HsFilePosition

/**
 * Created by vlad on 7/31/14.
 */

public class BreakpointListCommand(val module: String,
                                   val lineNumber: Int? = null,
                                   callback: SyncCommandCallback<FilePositionList?>)
: SyncCommand<FilePositionList?>(callback) {

    override fun getText(): String {
        if(lineNumber == null) {
            return ":breaklist $module\n"
        }
        return ":breaklist $module $lineNumber\n"
    }

    override fun parseGHCiOutput(output: Deque<String?>): FilePositionList? {
        throw RuntimeException("BreakpointListCommand.parseGHCiOutput: not supported in ghci")
    }

    override fun parseJSONOutput(output: JSONObject): FilePositionList? {
        throw NotImplementedException()
    }

    class object {
        public class DefaultCallback(syncObject: SyncObject, private val resultList: ArrayList<HsFilePosition>)
        : SyncCommandCallback<FilePositionList?>(syncObject) {
            override fun execAfterParsing(result: FilePositionList?) {
                if(result != null) {
                    for (filePos in result.list) {
                        resultList.add(filePos)
                    }
                }
            }
        }
    }
}