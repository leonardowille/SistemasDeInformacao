import 'dart:convert';

import 'package:examapp/models/Exam.dart';
import 'package:examapp/pages/examPage.dart';
import 'package:examapp/services/examService.dart';
import 'package:examapp/uiComponents/examList.dart';
import 'package:examapp/uiComponents/examListItem.dart';
import 'package:flutter/material.dart';

class ExamTab extends StatefulWidget {
  @override
  _ExamTabState createState() => _ExamTabState();
}

class _ExamTabState extends State<ExamTab> {
  ExamService examService = ExamService();

  List<Exam> exams = [];

  _goToExam(context) {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => ExamPage()));
  }

  _getMyExams() {
    examService.getMyExams((response) {
//      (JsonDecoder().convert(response.toString()) as List).forEach((exam) {
//        setState(() {
//          this.exams.add(Exam.fromJson(exam));
//        });
//      });
    }, () {});
  }

  Widget _listItem() {
    if (this.exams == null || this.exams.length == 0) {
      _getMyExams();
      return Text("Carregando...");
    }
    return ExamListItem(exam: exams[0]);
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Center(
          child: Container(
            width: double.infinity,
            height: 100,
            child: Text("Gráfico de exames"),
          ),
        ),
        Container(
          height: 50,
        ),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceAround,
          children: <Widget>[
            Text("Listagem de exames"),
            InkWell(
              child: Text("+"),
              onTap: () => _goToExam(context),
            )
          ],
        ),
        _listItem(),
      ],
    );
  }
}
