import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Canvas implements Printable {

    private Model model;

    public Canvas(Model model) {
        this.model = model;
    }

    public void printOnPaper() {
        PrinterJob printerJob = PrinterJob.getPrinterJob();
        printerJob.setPrintable(this);

        boolean yes = printerJob.printDialog();
        if (yes) {
            try {
                printerJob.print();
            } catch (PrinterException ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     *
     * @param graphics
     * @param pageFormat
     * @param page
     * @return
     * @throws PrinterException
     */
    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D graphics2D = (Graphics2D)graphics;

        graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

        graphics.drawString("hello ", 100, 100);

        return PAGE_EXISTS;
    }
}