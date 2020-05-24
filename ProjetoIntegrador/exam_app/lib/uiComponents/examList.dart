import 'package:examapp/models/Exam.dart';
import 'package:examapp/services/examService.dart';
import 'package:examapp/uiComponents/examListItem.dart';
import 'package:flutter/material.dart';

class ExamList extends StatefulWidget {
  @override
  _ExamListState createState() => _ExamListState();
}

class _ExamListState extends State<ExamList> {
  ExamService examService = ExamService();

  List<Exam> exams = [];

  _getMyExams() {
    examService.getMyExams((response) {
      response.data.forEach((examData) {
        setState(() {
          exams.add(Exam.fromJson(examData));
        });
      });
    }, () {});
  }

  _removeExam(Exam exam) {
    examService.removeExam(exam.id, (response) {
      setState(() {
        exams.remove(exam);
      });
    }, () {});
  }

  @override
  Widget build(BuildContext context) {
    if (exams == null || exams.length == 0) {
      _getMyExams();
      return Center(
        child: Text("Nenhum exame encontrado."),
      );
    }
    return Container(
      height: 250,
      width: double.infinity,
      child: ListView.builder(
        itemCount: exams.length,
        itemBuilder: (context, index) {
          return ExamListItem(
              exam: exams[index], removeExam: () => _removeExam(exams[index]));
        },
      ),
    );
  }
}
