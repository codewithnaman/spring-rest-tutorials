package com.codewithnaman.entity.embeddable;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Data
@Embeddable
public class RecordMetadata {

    @Column(name = "create_date", nullable = false, updatable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime createDate;

    @Column(name = "update_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime updateDate;

    public static RecordMetadata getRecordMetaDataWithCurrentTime(){
        RecordMetadata metadata = new RecordMetadata();
        metadata.setCreateDate(LocalDateTime.now());
        metadata.setUpdateDate(LocalDateTime.now());
        return metadata;
    }

    public static RecordMetadata getRecordMetaDataWithUpdateDateSetToCurrentTime(){
        RecordMetadata metadata = new RecordMetadata();
        metadata.setUpdateDate(LocalDateTime.now());
        return metadata;
    }
}
