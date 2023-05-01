package ie303.ietutorapi.controllers;

import ie303.ietutorapi.models.InstructorSubjectRequest;
import ie303.ietutorapi.models.Notification;
import ie303.ietutorapi.repositories.InstructorSubjectRequestRepository;
import ie303.ietutorapi.repositories.NotificationRepository;
import ie303.ietutorapi.repositories.SubjectRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class InstructorSubjectRequestController {
    @Autowired
    InstructorSubjectRequestRepository instructorSubjectRequestRepo;

    @Autowired
    SubjectRepository subjectRepo;

    @Autowired
    NotificationRepository notificationRepo;

    // Instructor có thể tạo request để đăng ký dạy học cho một môn học bất kỳ
    @PostMapping("/instructor-subject-requests")
    public ResponseEntity<?> createInstructorSubjectRequest(@RequestBody JsonBody jsonBody) {
        InstructorSubjectRequest instructorSubjectRequest = new InstructorSubjectRequest();
        instructorSubjectRequest.setInstructorId(jsonBody.instructorId);
        instructorSubjectRequest.setSubjectId(jsonBody.subjectId);
        instructorSubjectRequest.setMessage(jsonBody.message);
        instructorSubjectRequest.setApproved(false);
        instructorSubjectRequest.setCreatedAt(new java.util.Date());

        instructorSubjectRequestRepo.save(instructorSubjectRequest);

        // return the role request object with status code 200
        return ResponseEntity.ok(instructorSubjectRequest);
    }

    // Khi admin duyệt request, nếu request được duyệt thì instructor sẽ được thêm vào danh sách instructor của môn học đó
    @PutMapping("/instructor-subject-requests/{id}")
    public ResponseEntity<?> approveInstructorSubjectRequest(@PathVariable("id") String id) {
        var instructorSubjectRequest = instructorSubjectRequestRepo.findById(id);

        if (instructorSubjectRequest.isEmpty()) {
            return ResponseEntity.badRequest().body("No instructor subject request found");
        }

        // Đánh dấu request là đã được duyệt
        instructorSubjectRequest.get().setApproved(true);
        instructorSubjectRequestRepo.save(instructorSubjectRequest.get());

        // Thêm instructor vào danh sách instructor của môn học
        var subject = subjectRepo.findById(instructorSubjectRequest.get().getSubjectId());
        if (subject.isEmpty()) {
            return ResponseEntity.badRequest().body("No subject found");
        }
        subject.get().getInstructorIds().add(instructorSubjectRequest.get().getInstructorId());
        subjectRepo.save(subject.get());

        // Tạo notification cho instructor
        Notification notification = new Notification();
        notification.setUserId(instructorSubjectRequest.get().getInstructorId());
        notification.setMessage("Your request to teach " + subject.get().getName() + " has been approved");
        notification.setCreatedAt(new java.util.Date());
        notification.setRead(false);
        notificationRepo.save(notification);

        // return the role request object with status code 200
        return ResponseEntity.ok(instructorSubjectRequest.get());
    }

    @Getter
    public static class JsonBody {

        @Field("instructor_id")
        private String instructorId; // id của instructor gửi request

        @Field("subject_id")
        private String subjectId;
        private String message;
    }
}
