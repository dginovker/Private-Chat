package Program;

import Program.Client.Client;
import Program.Server.ChatServer;
import Program.Chatbox.Chatbox;
import Program.Setup.NamePrompt;

public class Program {
    public static void main(String[] args) throws Exception {
        Program.getInfo getInfo = new Program.getInfo();
        new NamePrompt(getInfo);

        while (!getInfo.isReady())
        {
            //Thread.sleep is a little sloppy, but a temporary change
            Thread.sleep(1000);
        }

        if (getInfo.isCreateServer())
        {
            createServer();
        }

        //Create the client
        Client client = new Client(getInfo.getName(), getInfo.getPassword());

        new Chatbox(client);
    }

    private static void createServer() throws InterruptedException {
        ChatServer server;
        try {
            //Make a server for clients to connect to
            server = new ChatServer();
            //Put it in its own thread
            Thread serverThread = new Thread(server);
            serverThread.start();
            while (!server.isReady())
            {
                System.out.println("Waiting for server to be ready");
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static class getInfo
    {
        private String name;
        private String password;
        private boolean createServer;
        private boolean ready = false;

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isCreateServer() {
            return createServer;
        }

        public void setCreateServer(boolean createServer) {
            this.createServer = createServer;
        }

        public boolean isReady() {
            return ready;
        }

        public void setReady(boolean ready) {
            this.ready = ready;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
