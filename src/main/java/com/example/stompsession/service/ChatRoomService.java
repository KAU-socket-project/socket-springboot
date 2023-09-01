package com.example.stompsession.service;

import com.example.stompsession.domain.ChatRoom;
import com.example.stompsession.repository.ChatRoomRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public Long save(ChatRoom chatRoom) {
        chatRoomRepository.save(chatRoom);
        return chatRoom.getId();
    }

    public ChatRoom findById(Long id) {
        Optional<ChatRoom> optionalChatRoom = chatRoomRepository.findById(id);
        if (optionalChatRoom.isPresent()) {
            return optionalChatRoom.get();
        } else {
            log.error("존재하지 않는 채팅방 ID입니다. : {}", id);
            return null;
        }
    }

    public List<ChatRoom> findAll() {
        return chatRoomRepository.findAllByOrderByIdDesc();
    }
}
