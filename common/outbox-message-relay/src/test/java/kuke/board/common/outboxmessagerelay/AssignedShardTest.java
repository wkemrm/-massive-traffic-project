package kuke.board.common.outboxmessagerelay;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AssignedShardTest {

    @Test
    void ofTest() {
        // given
        Long shardIdCount = 64L;
        List<String> appList = List.of("appId1", "appId2", "appId3");

        // when
        AssignedShard assignedShard1 = AssignedShard.of(appList.get(0), appList, shardIdCount);
        AssignedShard assignedShard2 = AssignedShard.of(appList.get(1), appList, shardIdCount);
        AssignedShard assignedShard3 = AssignedShard.of(appList.get(2), appList, shardIdCount);
        AssignedShard assignedShard4 = AssignedShard.of("invalid", appList, shardIdCount);

        // then
        List<Long> result = Stream.of(
                        assignedShard1.getShards(),
                        assignedShard2.getShards(),
                        assignedShard3.getShards(),
                        assignedShard4.getShards()
                )
                .flatMap(List::stream)
                .toList();

        assertEquals(result.size(), shardIdCount);

        for (int i = 0 ; i < 64 ; i++) {
            assertEquals(result.get(i), i);
        }

        assertEquals(assignedShard4.getShards().size(), 0);
    }

}