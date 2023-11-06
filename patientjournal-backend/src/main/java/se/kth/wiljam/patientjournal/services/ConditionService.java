package se.kth.wiljam.patientjournal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.wiljam.patientjournal.exception.UserNotFoundException;
import se.kth.wiljam.patientjournal.model.Condition;
import se.kth.wiljam.patientjournal.repository.ConditionRepository;

import java.util.List;

@Service
public class ConditionService {

    @Autowired
    private ConditionRepository conditionRepository;

    public List<Condition> list() {
        return conditionRepository.findAll();
    }

    public Condition newCondition(Condition condition) {
        return conditionRepository.save(condition);
    }

    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    public Condition getConditionById(Long id) {
        return conditionRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public Condition updateCondition(Condition condition, Long id) {
        return conditionRepository.findById(id)
                .map(con -> {
                    con.setAsserter(condition.getAsserter());
                    con.setEncounters(condition.getEncounters());
                    con.setPatient(condition.getPatient());
                    con.setSubject(condition.getSubject());
                    con.setEvidenceDetail(condition.getEvidenceDetail());
                    return conditionRepository.save(con);
                })
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public String deleteCondition(Long id) {
        if(!conditionRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        conditionRepository.deleteById(id);
        return "Condition with id " + id + " has been deleted sucessfully!";
    }
}
