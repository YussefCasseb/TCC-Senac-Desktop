package PDF;

import DAO.VendasDAO;
import Objetos.Vendas;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class VendasPDF {
    
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("file");
    private String local;
    
    public void setLocal(String local) {
        this.local = local;
    }
    
    public void GerarPDF(LocalDate dt, LocalDate dt2) {
        Document doc = new Document();
        
        VendasDAO vdao = new VendasDAO();

        try {
            PdfWriter.getInstance(doc, new FileOutputStream(local + ".pdf"));

            Font ftitulo = new Font(Font.FontFamily.TIMES_ROMAN, 50.0f, Font.BOLD, BaseColor.BLACK);
            Font fheader = new Font(Font.FontFamily.TIMES_ROMAN, 14.0f, Font.BOLD, BaseColor.BLACK);
            Font fcorpo = new Font(Font.FontFamily.TIMES_ROMAN, 12.0f, Font.NORMAL, BaseColor.BLACK);

            doc.open();
            
            doc.setPageSize(PageSize.A4);
            
            Paragraph p = new Paragraph("Relatório de Vendas", ftitulo);
            p.setAlignment(1);
            doc.add(p);
            p = new Paragraph("   ");
            doc.add(p);
            
            PdfPTable table = new PdfPTable(3);
            table.setWidths(new int[]{8,8,8});
            table.setWidthPercentage(100);
            
            PdfPCell coluna1 = new PdfPCell(new Paragraph("Data",fheader));
            PdfPCell coluna2 = new PdfPCell(new Paragraph("Produto",fheader));
            PdfPCell coluna3 = new PdfPCell(new Paragraph("Quantidade Vendida",fheader));
            
            table.addCell(coluna1);
            table.addCell(coluna2);
            table.addCell(coluna3);

            for (Vendas v : vdao.Search(String.valueOf(dt), String.valueOf(dt2))) {
                coluna1 = new PdfPCell(new Paragraph(v.getData(), fcorpo));
                coluna2 = new PdfPCell(new Paragraph(String.valueOf(v.getproduto()), fcorpo));
                coluna3 = new PdfPCell(new Paragraph(String.valueOf(v.getQtde_vendida()), fcorpo));
                table.addCell(coluna1);
                table.addCell(coluna2);
                table.addCell(coluna3);
            }
            
            doc.add(table);
            
        } catch (FileNotFoundException | DocumentException ex) {
            logger.error(ex);
        } finally{
            doc.close();
        }

    }
    
}
