package com.github.blanexie.vxpt.bbs.controller


import cn.dev33.satoken.stp.StpUtil
import cn.dev33.satoken.util.SaResult
import cn.hutool.core.net.URLEncodeUtil
import com.github.blanexie.vxpt.bbs.service.ResourceService
import com.github.blanexie.vxpt.bbs.util.Response
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import java.nio.charset.Charset
import javax.servlet.http.HttpServletResponse

/**
 *
 * @author ：xiezc
 * @date   ：2022/11/9 7:32 PM
 */
@Controller
class ResourceController(val resourceService: ResourceService) {

    @PostMapping("/api/upload/{fileName}")
    fun uploadFile(@PathVariable(name = "fileName") fileName: String, file: MultipartFile): SaResult {
        val loginId = StpUtil.getLoginId() as Int
        resourceService.upload(loginId, file.bytes, fileName)
        return SaResult.ok()
    }

    @GetMapping("/api/get/{fileName}")
    fun getFile(
        @PathVariable(name = "fileName") fileName: String, servletResponse: HttpServletResponse
    ) {
        val loginId = StpUtil.getLoginId() as Int
        val md5Hex = fileName.substringBeforeLast(".")
        val resDO = resourceService.download(loginId, md5Hex)
        servletResponse.reset()
        servletResponse.characterEncoding = "UTF-8"
        servletResponse.addHeader(
            "Content-Disposition",
            "attachment;filename=" + URLEncodeUtil.encode(fileName, Charset.forName("utf8"))
        )
        servletResponse.addHeader("Content-Length", "" + resDO.content.size);
        servletResponse.contentType = "application/octet-stream"

        servletResponse.outputStream.write(resDO.content)
        servletResponse.flushBuffer()
    }

}
