package com.tiep.demoapus.service;

import com.tiep.demoapus.entity.GroupReason;

import java.util.List;

public interface GroupReasonService {
    List<GroupReason> getAllGroupReasons();
    GroupReason getGroupReasonById(Long id);
    GroupReason addGroupReason(GroupReason groupReason);
    GroupReason updateGroupReason(GroupReason groupReason);
    void deleteGroupReason(Long id);
    boolean existsById(Long id);
}
