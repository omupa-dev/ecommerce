package livraria;

public class NotificarViaWhatsapp implements Notificacao {

    @Override
    public void notificarCompra(Cliente cliente) {
        System.out.println("Notificando via WhatsApp");
    }

}
