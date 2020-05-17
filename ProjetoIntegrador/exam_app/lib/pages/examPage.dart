import 'package:examapp/models/Exam.dart';
import 'package:examapp/services/examService.dart';
import 'package:examapp/uiComponents/inputComponent.dart';
import 'package:examapp/validators/notEmptyValidator.dart';
import 'package:flutter/material.dart';

class ExamPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    ExamService examService = ExamService();

    GlobalKey<FormState> _key = GlobalKey();
    TextEditingController _inputData = TextEditingController();
    TextEditingController _inputGlucose = TextEditingController();

    _submitForm(BuildContext context) {
      if (_key.currentState.validate()) {
        Exam exam = Exam();
        exam.date = _inputData.text;
        exam.glucose = _inputGlucose.text;
        print("date: ${_inputData.text} / glucose: ${_inputGlucose.text}");
        examService.createExam(
            exam, (response) => Navigator.pop(context), () {});
      }
    }

    Widget _widgetForm(BuildContext context) {
      return Column(
        children: <Widget>[
          InputComponent(
            hintText: "Data",
            controller: _inputData,
            validator: NotEmptyValidator(),
          ),
          InputComponent(
            hintText: "Glicose",
            controller: _inputGlucose,
            validator: NotEmptyValidator(),
          ),
          MaterialButton(
            child: Text("Salvar Exame"),
            onPressed: () => _submitForm(context),
          ),
        ],
      );
    }

    return SafeArea(
      child: Scaffold(
        appBar: AppBar(),
        body: Form(
          key: _key,
          child: _widgetForm(context),
        ),
      ),
    );
  }
}
