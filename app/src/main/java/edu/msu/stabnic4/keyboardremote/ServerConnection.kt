package edu.msu.stabnic4.keyboardremote

import java.io.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class ServerConnection {

    var server: String = ""
    var port: Int = 0


    var ary: ByteArray = ByteArray(1024)

    fun addToAry(value: String){
        ary += value.toByte()
    }

    /**
     * Initiates a connection with the server
     */
    fun startConnection(ip: String, port: Int) {

        server = ip
        this.port = port

        val url = URL("http://" + server + ":" + port.toString())

        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true

      //  connection.setRequestProperty("charset", "utf-8")
     //   connection.setRequestProperty("Content-length", ary.size.toString())
        connection.setRequestProperty("Content-Type", "application/json")
        connection.setRequestProperty("Accept", "application/json")


        thread(start = true){
            Thread.sleep(100)
            try {

                while(true) {
                    ary.set(0, 'f'.toByte())
                    val outputStream = connection.outputStream

                    outputStream.write(ary)
                    outputStream.close()
                 //   outputStream.flush()
                    ary = ByteArray(1024)
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}