package app.noah.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ImageContentDto
{
    private String originalFileName;
    private Integer fileSize;
    private String uploadFileName;
    private String filePath;
    private String fileType;

}
