package org.harry.love.game.server.channel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.harry.love.game.server.adapter.ServerProtoAdapter;
import org.springframework.beans.factory.annotation.Autowired;

public class SocketChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private ServerProtoAdapter serverProtoAdapter;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {

        ChannelPipeline pipeline = socketChannel.pipeline();

        pipeline
                .addLast(new ProtobufVarint32FrameDecoder())
//                .addLast("proto-decoder", new ProtobufDecoder(CmdProto.Cmd.getDefaultInstance()))
//                .addLast(new ProtobufVarint32LengthFieldPrepender())
//                .addLast("proto-encoder", new ProtobufEncoder())
                .addLast(serverProtoAdapter);
    }
}
