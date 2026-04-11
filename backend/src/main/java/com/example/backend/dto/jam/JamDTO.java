package com.example.backend.dto.jam;

import com.example.backend.dto.PlayerStateDTO;
import com.example.backend.dto.user.MemberProfilePreviewDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JamDTO {

    private Long id;
    private String code;
    private boolean isPublic;
    private int size;
    private List<MemberProfilePreviewDTO> members;
    private PlayerStateDTO playerState;

}
