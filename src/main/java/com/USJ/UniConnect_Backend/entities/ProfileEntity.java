package com.USJ.UniConnect_Backend.entities;

import com.USJ.UniConnect_Backend.dto.Certification;
import com.USJ.UniConnect_Backend.dto.Experience;
import com.USJ.UniConnect_Backend.dto.ProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="profiles")
public class ProfileEntity {

    @Id
    private long id;
    private String name;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private String about;
    private byte[] picture;
    private Long totalExp;
    private List<String> skills;
    private List<Experience> experience;
    private List<Certification> certifications;
    private List<Long> savedJobs;

    public ProfileDto toDto() {
        return new ProfileDto(this.id,this.name,this.email,this.jobTitle,this.company,this.location,this.about,this.picture!=null? Base64.getEncoder().encodeToString(this.picture):null,this.totalExp,this.skills,this.experience,this.certifications,this.savedJobs);
    }


}
