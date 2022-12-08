package com.adventofcode2022.day7

var outermostDir = FileSystemNode()
var pwd = outermostDir
var totalDiskSpace = 70000000
var neededFreeSpaceForUpdate = 30000000
var smallestDir = outermostDir

fun main() {
    outermostDir.name = "/"
    processCommands()
}

fun processCommands() {
    var line = readLine()
    while (line != null) {
        when {
            line.startsWith("$ cd") -> changeDir(line.replace("$ cd ", "").trim())
            line.startsWith("$ ls") -> Unit
            else -> processOutput(line)
        }
        line = readLine()
    }
    println(dirSumSize(100000, outermostDir))
    val neededFreeSpace = neededFreeSpaceForUpdate - (totalDiskSpace - outermostDir.fileSize)
    println("Needed Free Space $neededFreeSpace")
    getSmallestDirToDelete(neededFreeSpace, outermostDir)
    println("Smallest Dir Size ${smallestDir.fileSize}")

}

//Part II
fun getSmallestDirToDelete(space:Int, dir:FileSystemNode) {
    when {
        dir.fileSize > space && dir.fileSize <= smallestDir.fileSize -> smallestDir = dir
    }
    dir.fileSystemNodes.filter { n -> !n.isFile }.forEach { n -> getSmallestDirToDelete(space,n) }
}

//Part I
fun dirSumSize(maxSize:Int, currentDir: FileSystemNode):Int {
    var sum = 0
    for(n in currentDir.fileSystemNodes) {
        if(!n.isFile) {
            sum += dirSumSize(maxSize,n)
        }
    }
    return when {
        currentDir.fileSize < maxSize -> sum + currentDir.fileSize
        else -> sum
    }
}

fun changeDir(dir: String) {
    pwd = when (dir) {
        "/" -> outermostDir
        ".." -> pwd.parentDir
        else -> pwd.fileSystemNodes.find { d -> dir == d.name }!!
    }
}

fun processOutput(line: String) {
    val node = FileSystemNode()
    pwd.addFileSystemNode(node)
    node.parentDir = pwd
    when {
        line.startsWith("dir") -> {
            node.name = line.replace("dir ", "")
        }
        else -> {
            val fileInfo = line.split(" ")
            node.name = fileInfo[1]
            node.fileSize = fileInfo[0].toInt()
            updateParentDirSize(node.fileSize, node.parentDir)
            node.isFile = true
        }
    }
}

fun updateParentDirSize(fileSize: Int, parentDir: FileSystemNode) {
    parentDir.fileSize += fileSize
    if (parentDir.name != "/") {
        updateParentDirSize(fileSize, parentDir.parentDir)
    }
}

class FileSystemNode {
    var fileSystemNodes = mutableListOf<FileSystemNode>()
    lateinit var name: String
    var isFile = false
    var fileSize = 0
    lateinit var parentDir: FileSystemNode
    fun addFileSystemNode(node: FileSystemNode) {
        fileSystemNodes.add(node)
    }
}
