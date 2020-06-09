package app.noah.service;

import app.noah.repository.pouch.PouchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PouchService
{
    private final PouchRepository pouchRepository;


}
