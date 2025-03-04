package com.tiep.demoapus.service.ServiceImp;

import com.tiep.demoapus.entity.GroupReason;
import com.tiep.demoapus.repository.GroupReasonRepository;
import com.tiep.demoapus.service.GroupReasonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupReasonServiceImpl implements GroupReasonService {

    private final GroupReasonRepository groupReasonRepository;

    @Override
    public List<GroupReason> getAllGroupReasons() {
        return groupReasonRepository.findAll();
    }

    @Override
    public GroupReason getGroupReasonById(Long id) {
        return groupReasonRepository.findById(id).orElse(null);
    }

    @Override
    public GroupReason addGroupReason(GroupReason groupReason) {
        LocalDateTime now = LocalDateTime.now();
        if (groupReason.getCreated_at() == null) {
            groupReason.setCreated_at(now);
        }
        groupReason.setUpdated_at(now);
        return groupReasonRepository.save(groupReason);
    }

    @Override
    public GroupReason updateGroupReason(GroupReason groupReason) {
        groupReason.setUpdated_at(LocalDateTime.now());
        return groupReasonRepository.save(groupReason);
    }

    @Override
    public void deleteGroupReason(Long id) {
        GroupReason groupReason = groupReasonRepository.findById(id).orElse(null);
        if (groupReason != null) {
            groupReasonRepository.delete(groupReason);
        }
    }

    @Override
    public boolean existsById(Long id) {
        return groupReasonRepository.existsById(id);
    }
}
