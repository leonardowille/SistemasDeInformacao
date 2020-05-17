import 'package:examapp/models/Exam.dart';
import 'package:examapp/services/examService.dart';
import 'package:flutter/material.dart';

class ExamListItem extends StatelessWidget {
  ExamService examService = ExamService();

  final Exam exam;
  final Function removeExam;

  ExamListItem({this.exam, this.removeExam});

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Column(
        children: <Widget>[
          Text(exam.date),
          Text("Glicose: ${exam.glucose}"),
          InkWell(
            onTap: removeExam,
            child: Icon(
              Icons.restore_from_trash,
              color: Colors.red,
            ),
          )
        ],
      ),
    );
  }
}
