package org.example.resource;

public class RemoteResource implements IResource {
    @Override
    public void getResponse(){
        System.out.println("Remote Resource says hi");
    }
}
