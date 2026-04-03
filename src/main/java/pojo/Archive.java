package pojo;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Objects;
import java.io.Serial;


/**
 * 档案类
 * 用于表示和管理档案的基本信息
 *
 * @author gongjing
 */
public class Archive implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer archiveId;
    private String creator;
    private LocalDateTime timestamp;
    private String description;
    private String fileName;

    // 1. 必须通过构造器初始化 final 字段（无参构造器赋默认值，MyBatis兼容）
    public Archive() {
    }

    // 2. 全参构造器（核心构造方法）
    public Archive(String creator, LocalDateTime timestamp, String description, String fileName) {
        this.creator = creator;
        this.timestamp = timestamp;
        this.description = description;
        this.fileName = fileName;
    }

    /**
     * 获取档案号
     *
     * @return 档案号
     */
    public Integer getArchiveId() { return archiveId; }
    public void setArchiveId(Integer archiveId) { this.archiveId = archiveId; }
    /**
     * 获取档案创建者
     *
     * @return 档案创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置档案创建者
     *
     * @param creator 档案创建者
     */
    public void setCreator(String creator) {
        if (creator == null || creator.trim().isEmpty()) {
            throw new IllegalArgumentException("创建者不能为空");
        }
        this.creator = creator.trim();
    }

    /**
     * 获取时间戳
     *
     * @return 时间戳
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * 设置时间戳
     *
     * @param timestamp 时间戳
     */
    public void setTimestamp(LocalDateTime timestamp) {
        if (timestamp == null) {
            throw new IllegalArgumentException("时间戳不能为空");
        }
        this.timestamp = timestamp;
    }

    /**
     * 获取档案描述
     *
     * @return 档案描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置档案描述
     *
     * @param description 档案描述
     */
    public void setDescription(String description) {
        this.description = description.trim();
    }

    /**
     * 获取文件名
     *
     * @return 文件名
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名
     *
     * @param fileName 文件名
     */
    public void setFileName(String fileName) {
        this.fileName = fileName != null ? fileName.trim() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Archive archive = (Archive) o;
        return Objects.equals(archiveId, archive.archiveId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(archiveId);
    }

    @Override
    public String toString() {
        return "Archive{" +
                "archiveId='" + archiveId + '\'' +
                ", creator='" + creator + '\'' +
                ", timestamp=" + timestamp +
                ", description='" + description + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}

