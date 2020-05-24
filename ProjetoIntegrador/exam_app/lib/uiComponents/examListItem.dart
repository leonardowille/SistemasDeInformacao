import 'package:examapp/models/Exam.dart';
import 'package:examapp/pages/examPage.dart';
import 'package:examapp/services/examService.dart';
import 'package:flutter/material.dart';

class ExamListItem extends StatelessWidget {
  ExamService examService = ExamService();

  final Exam exam;
  final Function removeExam;

  ExamListItem({this.exam, this.removeExam});

  _editExam(context) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => ExamPage.startPage(exam: exam)));
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Padding(
        padding: const EdgeInsets.only(left: 8, right: 8),
        child: Row(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Container(
                decoration: BoxDecoration(
                  shape: BoxShape.circle,
                  color: Colors.grey,
                ),
                child: Padding(
                  padding: const EdgeInsets.all(8.0),
                  child: Icon(
                    Icons.local_hospital,
                    size: 36,
                  ),
                ),
              ),
            ),
            Column(
              children: <Widget>[
                Text(exam.date),
                Text("Glicose: ${exam.glucose}"),
              ],
            ),
            Spacer(),
            Row(
              children: <Widget>[
                InkWell(
                  onTap: () => _editExam(context),
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Icon(
                      Icons.edit,
                      color: Colors.grey,
                    ),
                  ),
                ),
                InkWell(
                  onTap: removeExam,
                  child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: Icon(
                      Icons.restore_from_trash,
                      color: Colors.grey,
                    ),
                  ),
                ),
              ],
            )
          ],
        ),
      ),
    );
  }
}
