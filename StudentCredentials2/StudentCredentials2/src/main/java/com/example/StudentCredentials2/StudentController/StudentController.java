package com.example.StudentCredentials2.StudentController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.example.StudentCredentials2.StudentCredentials.StudentCredentials;
import java.util.List;
@RestController
@RequestMapping
@CrossOrigin
public class StudentController {
    @Autowired
    MongoTemplate mongoTemplate;
    @GetMapping("/getStudent")
    public ResponseEntity<?> getAllStudent()
    {
        List<StudentCredentials> v = mongoTemplate.findAll(StudentCredentials.class);
        System.out.println(v);
        return new ResponseEntity<>(v,  HttpStatus.OK );
    }
    @GetMapping("/getStudent/{sid}")
    public ResponseEntity<?> getStudent(@PathVariable("sid") String id)
    {
        Query query=new Query();
        query.addCriteria(Criteria.where("sid").is(id));
        List<StudentCredentials> v= mongoTemplate.find(query, StudentCredentials.class);
        return new ResponseEntity<>(v, HttpStatus.OK);
    }
    @PutMapping("/updateStudent/{sid}")
    public ResponseEntity<?> updateStudent(@RequestBody StudentCredentials varch,@PathVariable("sid") String id )
    {
        Query query=new Query();
        query.addCriteria(Criteria.where("sid").is(id));
        Update update=new Update();
        update.set("sname",varch.getSname());
        update.set("sid",varch.getSid());
        update.set("susername",varch.getSusername());
        update.set("sclass",varch.getSclass());
        update.set("fatherName",varch.getFatherName());
        update.set("section",varch.getSection());
        update.set("sphonenumber",varch.getSphonenumber());
        update.set("srollNo",varch.getSrollNo());
//        update.set("Tid",varch.getTid());
//        update.set("Tname",varch.getTname());
        mongoTemplate.upsert(query, update, StudentCredentials.class);
        //service1.updateData(id,varch);
        return new ResponseEntity<>("Updated Student data with id "+id+ " by teacher ", HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudent/{sid}")
    public ResponseEntity<?> deleteStudent(@PathVariable("sid") String id)
    { try{
        Query query=new Query();
        query.addCriteria(Criteria.where("sid").is(id));
        List<StudentCredentials> v= mongoTemplate.find(query, StudentCredentials.class);
        mongoTemplate.findAndRemove(query, StudentCredentials.class);
        return new ResponseEntity<>("Successfully deleted todo with id "+id, HttpStatus.OK);}
    catch(Exception e)
    { return new ResponseEntity<>("unable to delete", HttpStatus.NOT_FOUND);}
    }
//    @PostMapping("/addStudent/{id}")
//    public ResponseEntity<?> addStudent(@RequestBody StudentCredentials varch1)
//    {
//        StudentCredentials v=new StudentCredentials();
//        v=varch1;
////        service1.addData(varch1);
//        mongoTemplate.save(v,"mergedData");
//        return new ResponseEntity<StudentCredentials>(varch1, HttpStatus.OK);
//    }
    @PostMapping("/addData")
    public ResponseEntity<?> addData(@RequestBody StudentCredentials varch)
    {
        //System.out.println(varch.toString());
//        StudentCredentials var = varch;

//        service1.addData(varch);
        if(varch.getSid()!=null){
        mongoTemplate.save(varch,"student");
        return new ResponseEntity<StudentCredentials>(varch,HttpStatus.OK);}
        return new ResponseEntity<StudentCredentials>(HttpStatus.FORBIDDEN);
    }

}
