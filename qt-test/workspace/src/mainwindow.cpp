#include "mainwindow.h"
#include "ui_mainwindow.h"

// Implement populate(). Do not change the public tab destinations.
//
// Home    -> homeLabel text equals "Hello from Qt"
// Status  -> statusLabel text equals "ok"
// Summary -> read data/items.csv from the process working directory
//            (workspace root). Display group, total, and skipped from
//            fromCsv.
//
// For Summary, skip the header row. Group by `category` and sum
// numeric `amount`. Skip rows with an empty category or a non-numeric
// amount. The winner is the category with the highest total; ties go to
// the lexicographically smallest category name.

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent), ui(new Ui::MainWindow) {
  ui->setupUi(this);
  setWindowTitle(QStringLiteral("test-app"));
  populate();
}

MainWindow::~MainWindow() = default;

void MainWindow::populate() {
  ui->homeLabel->setText(QStringLiteral("not implemented"));
  ui->statusLabel->setText(QStringLiteral("not implemented"));
  ui->groupLabel->setText(QStringLiteral("not implemented"));
  ui->totalLabel->setText(QStringLiteral("not implemented"));
  ui->skippedLabel->setText(QStringLiteral("not implemented"));
}
