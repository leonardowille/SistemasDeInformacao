import 'package:examapp/models/Exam.dart';
import 'package:flutter/material.dart';

class ExamListItem extends StatelessWidget {
  final Exam exam;

  ExamListItem({this.exam});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Text(exam.date),
        Text("Glicose: ${exam.glucose}"),
      ],
    );
  }
}
