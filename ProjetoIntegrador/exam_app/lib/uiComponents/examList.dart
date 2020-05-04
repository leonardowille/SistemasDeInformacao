import 'package:examapp/models/Exam.dart';
import 'package:examapp/uiComponents/examListItem.dart';
import 'package:flutter/material.dart';

class ExamList extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    var exam = Exam();
    exam.date = "17/10/1997";
    exam.glucose = "70";
    return Padding(
      padding: const EdgeInsets.only(top: 100),
      child: Container(
        child: ExamListItem(
          exam: exam,
        ),
      ),
    );
  }
}
